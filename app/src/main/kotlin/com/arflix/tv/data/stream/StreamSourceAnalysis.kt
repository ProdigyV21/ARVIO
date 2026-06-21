package com.arflix.tv.data.stream

import com.arflix.tv.data.model.StreamSource
import java.util.Locale

data class StreamSourceAnalysis(
    val stream: StreamSource,
    val rawTitle: String,
    val title: String,
    val addonLabel: String,
    val resolutionLabel: String,
    val resolutionScore: Int,
    val releaseLabel: String?,
    val releaseScore: Int,
    val codecLabel: String?,
    val visualTag: String?,
    val visualTags: List<String>,
    val audioLabel: String?,
    val audioTags: List<String>,
    val transportLabel: String?,
    val multiSourceLabel: String?,
    val languageLabel: String?,
    val hasSwedishSubtitles: Boolean,
    val chips: List<String>,
    val sizeBytes: Long,
    val isCachedOrDebridReady: Boolean,
    val isDirectHttp: Boolean
)

data class StreamAvailabilitySummary(
    val bestResolution: String?,
    val bestVisualTag: String?,
    val bestAudioTag: String?,
    val isCachedOrDebridReady: Boolean,
    val hasSwedishSubtitles: Boolean,
    val sourceCount: Int,
    val maxSizeLabel: String?,
    val bestSourceLabel: String?,
    val loadedAtMs: Long
)

private object StreamAnalysisRegexes {
    val AV1 = Regex("""\bAV1\b""", RegexOption.IGNORE_CASE)
    val HEVC = Regex("""\b(HEVC|X265|H265)\b""", RegexOption.IGNORE_CASE)
    val H264 = Regex("""\b(H264|X264|AVC)\b""", RegexOption.IGNORE_CASE)
    val REMUX = Regex("""\bREMUX\b""", RegexOption.IGNORE_CASE)
    val BLURAY = Regex("""\b(BLURAY|BDRIP|BDREMUX)\b""", RegexOption.IGNORE_CASE)
    val WEBDL = Regex("""\b(WEB[- .]?DL|WEBDL)\b""", RegexOption.IGNORE_CASE)
    val WEBRIP = Regex("""\bWEB[- .]?RIP\b""", RegexOption.IGNORE_CASE)
    val HDTV = Regex("""\bHDTV\b""", RegexOption.IGNORE_CASE)
    val CAM = Regex("""\b(CAM|TS|TELESYNC|HDCAM)\b""", RegexOption.IGNORE_CASE)
    val ATMOS = Regex("""\bATMOS\b""", RegexOption.IGNORE_CASE)
    val TRUEHD = Regex("""\bTRUEHD\b""", RegexOption.IGNORE_CASE)
    val DTS_X = Regex("""\bDTS[-_.: ]?X\b""", RegexOption.IGNORE_CASE)
    val DTS_HD_MA = Regex("""\bDTS[-_. ]?(?:HD[-_. ]?)?(?:MA|MASTER)\b""", RegexOption.IGNORE_CASE)
    val DTS_HD_ONLY = Regex("""\bDTS[-_. ]?HD\b""", RegexOption.IGNORE_CASE)
    val DTS = Regex("""\bDTS\b""", RegexOption.IGNORE_CASE)
    val DD_PLUS = Regex("""(?<![A-Z0-9])(DDP|DD\+|EAC-?3|E-?AC-?3)(?![A-Z0-9])""", RegexOption.IGNORE_CASE)
    val DD = Regex("""\b(AC-?3|DD(?!\+)(?:[ ._-]?5[ ._-]?1)?|DOLBY[ ._-]?DIGITAL)\b""", RegexOption.IGNORE_CASE)
    val AAC = Regex("""\bAAC\b""", RegexOption.IGNORE_CASE)
    val CH71 = Regex("""\b7[ .]?1\b""", RegexOption.IGNORE_CASE)
    val CH51 = Regex("""\b5[ .]?1\b""", RegexOption.IGNORE_CASE)
    val MULTI_AUDIO = Regex("""\b(MULTI|DUAL[ .-]?AUDIO|MULTI[ .-]?AUDIO)\b""", RegexOption.IGNORE_CASE)
    val LANGUAGE_HINT = Regex(
        """\b(ENG|ENGLISH|HIN|HINDI|TAM|TAMIL|TEL|TELUGU|JPN|JAPANESE|KOR|KOREAN|SPA|SPANISH|FRE|FRENCH|GER|GERMAN|ITA|ITALIAN)\b""",
        RegexOption.IGNORE_CASE
    )
    val DV = Regex("""\b(DV|DoVi|Dolby[\s._-]*Vision)\b""", RegexOption.IGNORE_CASE)
    val HDR10_PLUS = Regex("""(?<![A-Z0-9])(HDR10\+|HDR10\s*PLUS|HDR\s*10\s*\+)(?![A-Z0-9])""", RegexOption.IGNORE_CASE)
    val HDR10 = Regex("""\bHDR10(?!\+)""", RegexOption.IGNORE_CASE)
    val HDR = Regex("""\bHDR(10\+?|10)?\b""", RegexOption.IGNORE_CASE)
    val IMAX = Regex("""\bIMAX\b""", RegexOption.IGNORE_CASE)
    val WHITESPACE = Regex("""\s+""")
    val SIZE_PATTERN = Regex("""(\d+(?:\.\d+)?)\s*(TIB|GIB|MIB|KIB|TB|GB|MB|KB|B)""", RegexOption.IGNORE_CASE)
    val BARE_SIZE_PATTERN = Regex("""^(\d+)$""")
}

fun analyzeStreamSource(stream: StreamSource): StreamSourceAnalysis {
    val rawTitle = stream.behaviorHints?.filename?.takeIf { it.isNotBlank() } ?: stream.source
    val title = cleanSourceDisplayTitle(rawTitle)
    val addonLabel = stream.addonName.split(" - ").firstOrNull()?.trim().orEmpty()
        .ifBlank { stream.addonName }
    val searchBlob = buildSearchBlob(stream)

    val resolutionLabel = resolutionLabel(stream, searchBlob)
    val releaseLabel = releaseLabel(searchBlob)
    val codecLabel = codecLabel(searchBlob)
    val visualTags = visualTags(searchBlob)
    val audioTags = audioTags(searchBlob)
    val multiSourceLabel = when {
        stream.sources.size > 1 -> "${stream.sources.size} sources"
        stream.sources.size == 1 -> "1 source"
        else -> null
    }
    val languageLabel = languageLabel(stream, searchBlob)
    val hasSwedishSubtitles = hasSwedishSubtitles(stream)
    val isDebridReady = isDebridLikeSource(stream, searchBlob)
    val isReady = stream.behaviorHints?.cached == true || isDebridReady
    val isDirectHttp = !stream.url.isNullOrBlank() && stream.url.startsWith("http", ignoreCase = true)
    val transportLabel = transportLabel(
        stream = stream,
        addonLabel = addonLabel,
        searchBlob = searchBlob,
        isDebridReady = isDebridReady,
        isDirectHttp = isDirectHttp
    )

    val chips = buildList {
        add(addonLabel)
        transportLabel?.let(::add)
        multiSourceLabel?.let(::add)
        languageLabel?.let(::add)
        if (hasSwedishSubtitles) add("SE subs")
        releaseLabel?.let(::add)
        codecLabel?.let(::add)
        visualTags.forEach(::add)
        audioTags.firstOrNull()?.let(::add)
        if (stream.size.isNotBlank()) add(stream.size)
    }.distinct()

    return StreamSourceAnalysis(
        stream = stream,
        rawTitle = rawTitle,
        title = title,
        addonLabel = addonLabel,
        resolutionLabel = resolutionLabel,
        resolutionScore = resolutionScore(resolutionLabel),
        releaseLabel = releaseLabel,
        releaseScore = releaseScore(releaseLabel),
        codecLabel = codecLabel,
        visualTag = visualTags.firstOrNull(),
        visualTags = visualTags,
        audioLabel = audioTags.firstOrNull(),
        audioTags = audioTags,
        transportLabel = transportLabel,
        multiSourceLabel = multiSourceLabel,
        languageLabel = languageLabel,
        hasSwedishSubtitles = hasSwedishSubtitles,
        chips = chips,
        sizeBytes = parseSizeString(stream.size),
        isCachedOrDebridReady = isReady,
        isDirectHttp = isDirectHttp
    )
}

fun summarizeStreamAvailability(
    streams: List<StreamSource>,
    loadedAtMs: Long = System.currentTimeMillis()
): StreamAvailabilitySummary? {
    val analyses = streams.map(::analyzeStreamSource)
    if (analyses.isEmpty()) return null

    val best = bestStreamAvailabilityAnalysis(analyses) ?: return null

    return StreamAvailabilitySummary(
        bestResolution = best.resolutionLabel,
        bestVisualTag = best.visualTag,
        bestAudioTag = best.audioLabel,
        isCachedOrDebridReady = best.isCachedOrDebridReady,
        hasSwedishSubtitles = analyses.any { it.hasSwedishSubtitles },
        sourceCount = streams.size,
        maxSizeLabel = analyses.maxByOrNull { it.sizeBytes }?.stream?.size?.takeIf { it.isNotBlank() },
        bestSourceLabel = best.addonLabel,
        loadedAtMs = loadedAtMs
    )
}

fun bestStreamAvailabilityAnalysis(
    analyses: List<StreamSourceAnalysis>
): StreamSourceAnalysis? {
    return analyses.maxWithOrNull(
        compareBy<StreamSourceAnalysis> { it.resolutionScore }
            .thenBy { it.releaseScore }
            .thenBy { it.sizeBytes }
            .thenBy { if (it.isCachedOrDebridReady) 1 else 0 }
    )
}

fun bestStreamAvailabilityAnalysisForStreams(
    streams: List<StreamSource>
): StreamSourceAnalysis? {
    return bestStreamAvailabilityAnalysis(streams.map(::analyzeStreamSource))
}

fun parseSizeString(size: String): Long {
    if (size.isBlank()) return 0L

    val normalized = size.uppercase(Locale.US)
        .replace(",", ".")
        .replace(StreamAnalysisRegexes.WHITESPACE, " ")
        .trim()

    StreamAnalysisRegexes.SIZE_PATTERN.find(normalized)?.let { match ->
        val number = match.groupValues[1].toDoubleOrNull() ?: return@let
        val unit = match.groupValues[2].replace("IB", "B")
        return calculateBytes(number, unit)
    }

    StreamAnalysisRegexes.BARE_SIZE_PATTERN.find(normalized)?.let { match ->
        return match.groupValues[1].toLongOrNull() ?: 0L
    }

    return 0L
}

private fun buildSearchBlob(stream: StreamSource): String = buildString {
    append(stream.quality)
    append(' ')
    append(stream.source)
    append(' ')
    append(stream.addonName)
    append(' ')
    append(stream.behaviorHints?.filename.orEmpty())
    append(' ')
    append(stream.url.orEmpty())
    append(' ')
    append(stream.description.orEmpty())
}

private fun resolutionLabel(stream: StreamSource, searchBlob: String): String {
    return when {
        searchBlob.contains("2160p", ignoreCase = true) || searchBlob.contains("4K", ignoreCase = true) -> "4K"
        searchBlob.contains("1080p", ignoreCase = true) -> "1080p"
        searchBlob.contains("720p", ignoreCase = true) -> "720p"
        searchBlob.contains("480p", ignoreCase = true) -> "480p"
        StreamAnalysisRegexes.CAM.containsMatchIn(searchBlob) -> "CAM"
        else -> stream.quality.split(" ").firstOrNull()?.take(8) ?: "SD"
    }
}

private fun releaseLabel(searchBlob: String): String? = when {
    StreamAnalysisRegexes.REMUX.containsMatchIn(searchBlob) -> "REMUX"
    StreamAnalysisRegexes.BLURAY.containsMatchIn(searchBlob) -> "BluRay"
    StreamAnalysisRegexes.WEBDL.containsMatchIn(searchBlob) -> "WEB-DL"
    StreamAnalysisRegexes.WEBRIP.containsMatchIn(searchBlob) -> "WEBRip"
    StreamAnalysisRegexes.HDTV.containsMatchIn(searchBlob) -> "HDTV"
    StreamAnalysisRegexes.CAM.containsMatchIn(searchBlob) -> "CAM"
    else -> null
}

private fun codecLabel(searchBlob: String): String? = when {
    StreamAnalysisRegexes.AV1.containsMatchIn(searchBlob) -> "AV1"
    StreamAnalysisRegexes.HEVC.containsMatchIn(searchBlob) -> "HEVC"
    StreamAnalysisRegexes.H264.containsMatchIn(searchBlob) -> "H.264"
    else -> null
}

private fun visualTags(searchBlob: String): List<String> = buildList {
    if (StreamAnalysisRegexes.DV.containsMatchIn(searchBlob)) add("DV")
    when {
        StreamAnalysisRegexes.HDR10_PLUS.containsMatchIn(searchBlob) -> add("HDR10+")
        StreamAnalysisRegexes.HDR10.containsMatchIn(searchBlob) -> add("HDR10")
        StreamAnalysisRegexes.HDR.containsMatchIn(searchBlob) -> add("HDR")
    }
    if (StreamAnalysisRegexes.IMAX.containsMatchIn(searchBlob)) add("IMAX")
}.distinct()

private fun audioTags(searchBlob: String): List<String> = buildList {
    if (StreamAnalysisRegexes.ATMOS.containsMatchIn(searchBlob)) add("Atmos")
    if (StreamAnalysisRegexes.TRUEHD.containsMatchIn(searchBlob)) add("TrueHD")
    if (StreamAnalysisRegexes.DTS_X.containsMatchIn(searchBlob)) add("DTS:X")
    when {
        StreamAnalysisRegexes.DTS_HD_MA.containsMatchIn(searchBlob) -> add("DTS-HD MA")
        StreamAnalysisRegexes.DTS_HD_ONLY.containsMatchIn(searchBlob) -> add("DTS-HD")
    }
    if (StreamAnalysisRegexes.DD_PLUS.containsMatchIn(searchBlob)) add("DD+")
    if (StreamAnalysisRegexes.DD.containsMatchIn(searchBlob)) add("DD")
    if (StreamAnalysisRegexes.CH71.containsMatchIn(searchBlob)) add("7.1")
    if (StreamAnalysisRegexes.CH51.containsMatchIn(searchBlob)) add("5.1")
    if (StreamAnalysisRegexes.DTS.containsMatchIn(searchBlob) && none { it.startsWith("DTS") }) add("DTS")
    if (StreamAnalysisRegexes.AAC.containsMatchIn(searchBlob)) add("AAC")
}.distinct()

private fun transportLabel(
    stream: StreamSource,
    addonLabel: String,
    searchBlob: String,
    isDebridReady: Boolean,
    isDirectHttp: Boolean
): String? {
    val addonLower = addonLabel.lowercase(Locale.US)
    val isTorrentProvider =
        addonLower.contains("torrentio") ||
            addonLower.contains("torrent") ||
            addonLower.contains("debrid") ||
            addonLower.contains("realdebrid") ||
            addonLower.contains("premiumize") ||
            addonLower.contains("alldebrid") ||
            searchBlob.contains("magnet:", ignoreCase = true)
    val isIptvVod = stream.addonId == "iptv_xtream_vod" || addonLower.contains("iptv vod")

    return when {
        stream.behaviorHints?.cached == true -> "Cached"
        isDebridReady -> "Debrid"
        !stream.infoHash.isNullOrBlank() || stream.sources.isNotEmpty() || isTorrentProvider -> "Torrent"
        isIptvVod && isDirectHttp -> "VOD"
        else -> null
    }
}

private fun languageLabel(stream: StreamSource, searchBlob: String): String? {
    val subtitleLangs = stream.subtitles.mapNotNull { sub ->
        sub.lang.takeIf { it.isNotBlank() }
    }.distinct()

    return when {
        StreamAnalysisRegexes.MULTI_AUDIO.containsMatchIn(searchBlob) -> "Multi-audio"
        subtitleLangs.size > 1 -> "${subtitleLangs.size} langs"
        subtitleLangs.size == 1 -> subtitleLangs.first().uppercase(Locale.US)
        else -> StreamAnalysisRegexes.LANGUAGE_HINT.find(searchBlob)?.value?.uppercase(Locale.US)
    }
}

private fun hasSwedishSubtitles(stream: StreamSource): Boolean {
    return stream.subtitles.any { subtitle ->
        val text = listOf(subtitle.lang, subtitle.label, subtitle.id)
            .joinToString(" ")
            .lowercase(Locale.US)
        text == "sv" ||
            text == "swe" ||
            text.contains("sv-se") ||
            text.contains("swedish") ||
            text.contains("svenska") ||
            Regex("""(^|[^a-z])sv([^a-z]|$)""").containsMatchIn(text) ||
            Regex("""(^|[^a-z])swe([^a-z]|$)""").containsMatchIn(text)
    }
}

private fun isDebridLikeSource(stream: StreamSource, blob: String): Boolean {
    val addonName = stream.addonName
    return addonName.contains("torbox", ignoreCase = true) ||
        addonName.contains("torrentio tb", ignoreCase = true) ||
        addonName.contains("torrentio rd", ignoreCase = true) ||
        addonName.contains("torrentio pm", ignoreCase = true) ||
        addonName.contains("torrentio ad", ignoreCase = true) ||
        blob.contains("debrid", ignoreCase = true) ||
        blob.contains("real-debrid", ignoreCase = true) ||
        blob.contains("realdebrid", ignoreCase = true) ||
        blob.contains("premiumize", ignoreCase = true) ||
        blob.contains("alldebrid", ignoreCase = true) ||
        blob.contains(" RD+", ignoreCase = true) ||
        blob.contains("[RD+]", ignoreCase = true) ||
        blob.contains(" TB+", ignoreCase = true) ||
        blob.contains("[TB+]", ignoreCase = true) ||
        blob.contains("torbox", ignoreCase = true)
}

private fun cleanSourceDisplayTitle(raw: String): String {
    val oneLine = raw
        .replace('\n', ' ')
        .replace('\r', ' ')
        .replace(StreamAnalysisRegexes.WHITESPACE, " ")
        .trim()

    if (oneLine.length <= 92) return oneLine.ifBlank { "Unknown source" }

    val withoutExtension = oneLine
        .replace(Regex("""\.(mkv|mp4|avi|mov|ts)$""", RegexOption.IGNORE_CASE), "")
    val compact = withoutExtension
        .replace(Regex("""\b(19|20)\d{2}\b.*"""), "")
        .replace('.', ' ')
        .replace('_', ' ')
        .replace(StreamAnalysisRegexes.WHITESPACE, " ")
        .trim()
        .takeIf { it.length in 8..70 }

    return compact ?: oneLine.take(92).trimEnd('.', ' ', '-', '_')
}

private fun resolutionScore(label: String): Int = when (label) {
    "4K" -> 4
    "1080p" -> 3
    "720p" -> 2
    "480p" -> 1
    "CAM" -> 0
    else -> 1
}

private fun releaseScore(label: String?): Int = when (label) {
    "REMUX" -> 5
    "BluRay" -> 4
    "WEB-DL" -> 3
    "WEBRip" -> 2
    "HDTV" -> 1
    else -> 0
}

private fun calculateBytes(number: Double, unit: String): Long {
    return when (unit) {
        "TB" -> (number * 1024.0 * 1024.0 * 1024.0 * 1024.0).toLong()
        "GB" -> (number * 1024.0 * 1024.0 * 1024.0).toLong()
        "MB" -> (number * 1024.0 * 1024.0).toLong()
        "KB" -> (number * 1024.0).toLong()
        "B" -> number.toLong()
        else -> 0L
    }
}
